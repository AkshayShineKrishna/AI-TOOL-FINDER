package com.trumio.task.aitools.cmdrunner;

import com.trumio.task.aitools.models.AITool;
import com.trumio.task.aitools.models.PricingType;
import com.trumio.task.aitools.models.Review;
import com.trumio.task.aitools.models.ReviewStatus;
import com.trumio.task.aitools.repositories.AIToolRepository;
import com.trumio.task.aitools.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CmdRunner implements CommandLineRunner {


    private final AIToolRepository aiToolRepository;

    private final ReviewRepository reviewRepository;

    CmdRunner(AIToolRepository aiToolRepository,ReviewRepository repository){
        this.aiToolRepository =aiToolRepository;
        this.reviewRepository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        // ---- ChatGPT ----
        AITool chatGpt = aiToolRepository.findByName("ChatGPT")
                .orElseGet(() -> {
                    AITool tool = new AITool();
                    tool.setName("ChatGPT");
                    tool.setUseCase("Conversational AI");
                    tool.setCategory("Productivity");
                    tool.setPricingType(PricingType.SUBSCRIPTION);
                    return aiToolRepository.save(tool);
                });

        if (!reviewRepository.existsByToolId(chatGpt.getId())) {
            Review r1 = new Review();
            r1.setToolId(chatGpt.getId());
            r1.setRating(5);
            r1.setComment("Excellent conversational abilities!");
            r1.setStatus(ReviewStatus.APPROVED);

            Review r2 = new Review();
            r2.setToolId(chatGpt.getId());
            r2.setRating(4);
            r2.setComment("Very helpful for coding.");
            r2.setStatus(ReviewStatus.APPROVED);

            reviewRepository.saveAll(List.of(r1, r2));
        }

        // ---- Midjourney ----
        AITool midjourney = aiToolRepository.findByName("Midjourney")
                .orElseGet(() -> {
                    AITool tool = new AITool();
                    tool.setName("Midjourney");
                    tool.setUseCase("AI Image Generation");
                    tool.setCategory("Design");
                    tool.setPricingType(PricingType.PAID);
                    return aiToolRepository.save(tool);
                });

        if (!reviewRepository.existsByToolId(midjourney.getId())) {
            Review r3 = new Review();
            r3.setToolId(midjourney.getId());
            r3.setRating(5);
            r3.setComment("Amazing image quality.");
            r3.setStatus(ReviewStatus.APPROVED);

            reviewRepository.save(r3);
        }

        System.out.println("Sample AI tools and reviews verified/inserted successfully.");
    }
}
