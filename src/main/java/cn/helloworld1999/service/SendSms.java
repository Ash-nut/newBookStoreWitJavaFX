/*
package cn.helloworld1999.service;

import com.aliyun.auth.credentials.Credential;
import com.aliyun.auth.credentials.provider.StaticCredentialProvider;
import com.aliyun.dysmsapi20170525.models.*;
import com.aliyun.dysmsapi20170525.Client;
import com.fasterxml.jackson.databind.ObjectMapper;
import darabonba.core.client.ClientOverrideConfiguration;

public class SendSms {
    public static void main(String[] args) throws Exception {
        sendSms("15146878867", "测试账号", "SMS_301890017", "{\"code\":\"666666\"}");
    }

    public static void sendSms(String phoneNumber, String signName, String templateCode, String templateParam) throws Exception {
        // 配置凭证认证信息，包括ak、secret
        StaticCredentialProvider provider = StaticCredentialProvider.create(Credential.builder()
                .accessKeyId(System.getenv("ALIBABA_CLOUD_ACCESS_KEY_ID"))
                .accessKeySecret(System.getenv("ALIBABA_CLOUD_ACCESS_KEY_SECRET"))
                .build());

        // 配置客户端
        Client client = new Client(ClientOverrideConfiguration.create()
                .setEndpointOverride("dysmsapi.aliyuncs.com")
                .credentialsProvider(provider)
        );

        // API请求的参数设置
        SendSmsRequest sendSmsRequest = SendSmsRequest.builder()
                .phoneNumbers(phoneNumber)
                .signName(signName)
                .templateCode(templateCode)
                .templateParam(templateParam)
                .build();

        // 同步获取API请求的返回值
        SendSmsResponse resp = client.sendSms(sendSmsRequest);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(resp));

        // 最后，关闭客户端
        client.close();
    }
}
*/
