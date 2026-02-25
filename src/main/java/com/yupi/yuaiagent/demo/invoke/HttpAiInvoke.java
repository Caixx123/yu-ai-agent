package com.yupi.yuaiagent.demo.invoke;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;

public class HttpAiInvoke {
    
    public static void main(String[] args) {
        String apiKey = TestApiKey.API_KEY; // 或者直接设置API密钥

        // 构建请求体数据
        String requestBody = JSONUtil.toJsonStr(new Object() {
            public String model = "qwen3.5-plus";
            public Input input = new Input();
            public Parameters parameters = new Parameters();
        });

        // 发送POST请求
        String result = HttpRequest.post("https://dashscope.aliyuncs.com/api/v1/services/aigc/text-generation/generation")
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .body(requestBody)
                .timeout(30000) // 设置超时时间（毫秒）
                .execute()
                .body();

        System.out.println(result);
    }
    
    // 内部类定义输入结构
    private static class Input {
        public Message[] messages = {
            new Message("system", "You are a helpful assistant."),
            new Message("user", "你是谁？")
        };
    }
    
    // 消息类
    private static class Message {
        public String role;
        public String content;
        
        public Message(String role, String content) {
            this.role = role;
            this.content = content;
        }
    }
    
    // 参数类
    private static class Parameters {
        public String result_format = "message";
    }
}
