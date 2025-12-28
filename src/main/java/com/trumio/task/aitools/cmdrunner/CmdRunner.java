package com.trumio.task.aitools.cmdrunner;

import com.trumio.task.aitools.models.AITool;
import com.trumio.task.aitools.models.PricingType;
import com.trumio.task.aitools.models.Review;
import com.trumio.task.aitools.models.ReviewStatus;
import com.trumio.task.aitools.repositories.AIToolRepository;
import com.trumio.task.aitools.repositories.ReviewRepository;
import com.trumio.task.aitools.services.ratingServices.RatingServices;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class CmdRunner implements CommandLineRunner {


    private final AIToolRepository aiToolRepository;

    private final ReviewRepository reviewRepository;

    private final RatingServices ratingServices;

    CmdRunner(AIToolRepository aiToolRepository, ReviewRepository repository, RatingServices ratingServices){
        this.aiToolRepository =aiToolRepository;
        this.reviewRepository = repository;
        this.ratingServices = ratingServices;
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
                    tool.setAverageRating(0D);
                    tool.setCreatedAt(LocalDateTime.now());
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
                    tool.setAverageRating(0D);
                    tool.setCreatedAt(LocalDateTime.now());
                    return aiToolRepository.save(tool);
                });

        if (!reviewRepository.existsByToolId(midjourney.getId())) {
            Review r3 = new Review();
            r3.setToolId(midjourney.getId());
            r3.setRating(2);
            r3.setComment("Amazing image quality.");
            r3.setStatus(ReviewStatus.APPROVED);

            reviewRepository.save(r3);
        }

        // ---- Perplexity ----
        AITool perplexity = aiToolRepository.findByName("Perplexity")
                .orElseGet(() -> {
                    AITool tool = new AITool();
                    tool.setName("Perplexity");
                    tool.setUseCase("AI Search Engine");
                    tool.setCategory("Research");
                    tool.setPricingType(PricingType.FREE);
                    tool.setAverageRating(0D);
                    tool.setCreatedAt(LocalDateTime.now());
                    return aiToolRepository.save(tool);
                });

        if (!reviewRepository.existsByToolId(perplexity.getId())) {
            Review r4 = new Review();
            r4.setToolId(perplexity.getId());
            r4.setRating(4);
            r4.setComment("Great for quick factual answers.");
            r4.setStatus(ReviewStatus.APPROVED);

            Review r5 = new Review();
            r5.setToolId(perplexity.getId());
            r5.setRating(3);
            r5.setComment("Sometimes shallow but useful.");
            r5.setStatus(ReviewStatus.APPROVED);

            reviewRepository.saveAll(List.of(r4, r5));
        }

        // ---- Copilot ----
        AITool copilot = aiToolRepository.findByName("Copilot")
                .orElseGet(() -> {
                    AITool tool = new AITool();
                    tool.setName("Copilot");
                    tool.setUseCase("AI Coding Assistant");
                    tool.setCategory("Development");
                    tool.setPricingType(PricingType.SUBSCRIPTION);
                    tool.setAverageRating(0D);
                    tool.setCreatedAt(LocalDateTime.now());
                    return aiToolRepository.save(tool);
                });

        if (!reviewRepository.existsByToolId(copilot.getId())) {
            Review r6 = new Review();
            r6.setToolId(copilot.getId());
            r6.setRating(5);
            r6.setComment("Excellent for speeding up coding tasks.");
            r6.setStatus(ReviewStatus.APPROVED);

            Review r7 = new Review();
            r7.setToolId(copilot.getId());
            r7.setRating(4);
            r7.setComment("Good suggestions, though not perfect.");
            r7.setStatus(ReviewStatus.APPROVED);

            reviewRepository.saveAll(List.of(r6, r7));
        }

        // ---- Claude ----
        AITool claude = aiToolRepository.findByName("Claude")
                .orElseGet(() -> {
                    AITool tool = new AITool();
                    tool.setName("Claude");
                    tool.setUseCase("Conversational AI");
                    tool.setCategory("Productivity");
                    tool.setPricingType(PricingType.SUBSCRIPTION);
                    tool.setAverageRating(0D);
                    tool.setCreatedAt(LocalDateTime.now());
                    return aiToolRepository.save(tool);
                });

        if (!reviewRepository.existsByToolId(claude.getId())) {
            Review r8 = new Review();
            r8.setToolId(claude.getId());
            r8.setRating(4);
            r8.setComment("Very natural conversation style.");
            r8.setStatus(ReviewStatus.APPROVED);

            Review r9 = new Review();
            r9.setToolId(claude.getId());
            r9.setRating(5);
            r9.setComment("Great for summarization tasks.");
            r9.setStatus(ReviewStatus.APPROVED);

            reviewRepository.saveAll(List.of(r8, r9));
        }

        // ---- Gemini ----
        AITool gemini = aiToolRepository.findByName("Gemini")
                .orElseGet(() -> {
                    AITool tool = new AITool();
                    tool.setName("Gemini");
                    tool.setUseCase("Multimodal AI");
                    tool.setCategory("General AI");
                    tool.setPricingType(PricingType.PAID);
                    tool.setAverageRating(0D);
                    tool.setCreatedAt(LocalDateTime.now());
                    return aiToolRepository.save(tool);
                });

        if (!reviewRepository.existsByToolId(gemini.getId())) {
            Review r10 = new Review();
            r10.setToolId(gemini.getId());
            r10.setRating(5);
            r10.setComment("Handles text and images seamlessly.");
            r10.setStatus(ReviewStatus.APPROVED);

            Review r11 = new Review();
            r11.setToolId(gemini.getId());
            r11.setRating(3);
            r11.setComment("Still evolving but promising.");
            r11.setStatus(ReviewStatus.APPROVED);

            reviewRepository.saveAll(List.of(r10, r11));
        }

        // ---- Calculate ratings ----
        ratingServices.calculateAllRatings();
        System.out.println("Sample AI tools and reviews verified/inserted successfully.");
    }
}
