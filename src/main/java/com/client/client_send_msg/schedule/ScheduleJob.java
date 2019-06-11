package com.client.client_send_msg.schedule;

import com.client.client_send_msg.utils.JdbcUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.*;

@Component

public class ScheduleJob {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JdbcUtils.getDataSource());

    @Scheduled(cron = "0 * * * * *", zone = "UTC")
    public void reportToCenter() throws Exception {
        try {
            System.out.println("new job");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
            calendar.add(Calendar.DAY_OF_MONTH, 0);


            String sql = "select remote_host, sum(share1_count) from t_user_share where  date(create_time) = ? and user_oid = '816061' group by remote_host;";
            //jdbcTemplate.update(sql,"2019-06-13");
            System.out.println(sql);
            System.out.println(sdf.format(calendar.getTime()));

            List rows = jdbcTemplate.queryForList(sql, sdf.format(calendar.getTime()));

            Object[] myrows = rows.toArray();
            JSONArray jsonArray = JSONArray.fromObject(myrows);

            for (int j = 0; j < jsonArray.size(); j++) {
                JSONObject jsonObject = jsonArray.getJSONObject(j);
                String remote_host = jsonObject.getString("remote_host");
                String id = jsonObject.getString("id");
                String share1_count = jsonObject.getString("sum(share1_count)");
                JSONObject postData = new JSONObject();

                postData.put("id", id);
                postData.put("remote_host", remote_host);
                postData.put("share1_count", share1_count);
                postData.put("dataTime", sdf.format(calendar.getTime()));
                RestTemplate client = new RestTemplate();
                String url = ResourceBundle.getBundle("application").getString("center.url");
                JSONObject msg=  client.postForEntity(url, postData, JSONObject.class).getBody();

                System.out.println(msg.toString());

            }


            System.out.println(" >>cron执行....");

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception();
        }

    }
}
