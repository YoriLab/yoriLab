package com.zolPro.yoriLab.service;

import com.zolPro.yoriLab.domain.Member;
import com.zolPro.yoriLab.dto.ClusterRequestBody;
import com.zolPro.yoriLab.dto.ClusterResponseBody;
import com.zolPro.yoriLab.dto.RecommRequestBody;
import com.zolPro.yoriLab.dto.RecommResponseBody;
import lombok.AllArgsConstructor;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class OtherApiService {
    private final String API_URL = "http://127.0.0.1:642";
    private RestTemplate restTemplate;


    public ClusterResponseBody fetchCluster(List<String> likeStr) {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(10*1000);
        factory.setReadTimeout(30*1000);

        restTemplate = new RestTemplate(factory);

        ClusterRequestBody requestBody = new ClusterRequestBody(likeStr);

        HttpHeaders headers=new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ClusterRequestBody> entity= new HttpEntity<>(requestBody, headers);

        ClusterResponseBody body = restTemplate.postForObject(API_URL + "/users/cluster", entity, ClusterResponseBody.class);
        System.out.println("body = " + body);
        return body;

    }


    public RecommResponseBody fetchRecomm(int day, Member member) {
        restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());


        RecommRequestBody requestBody = RecommRequestBody.builder()
                .day(day)
                .dishCluster(member.getDishCluster())
                .dishPointer(member.getDishPointer())
                .soupCluster(member.getSoupCluster())
                .soupPointer(member.getSoupPointer())
                .build();

        HttpHeaders headers=new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<RecommRequestBody> entity= new HttpEntity<>(requestBody, headers);

        RecommResponseBody body = restTemplate.postForObject(API_URL + "/users/recomm", entity, RecommResponseBody.class);
        System.out.println("body = " + body);

        return body;
    }

}
