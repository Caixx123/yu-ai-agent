package com.yupi.yuaiagent.demo.invoke;

import jakarta.annotation.Resource;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;

/**
 * Spring AI 框架调用Ollama大模型
 */
//@Component
public class OllamaAiInvoke implements CommandLineRunner {

    @Resource
    @Qualifier("ollamaChatModel")
    private ChatModel ollamaChatModel;

    @Override
    public void run(String... args) throws Exception {
        AssistantMessage output = ollamaChatModel.call(new Prompt("你好，我是jay")).getResult().getOutput();
        System.out.println(output.getText());
    }
}
