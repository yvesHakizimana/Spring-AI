package com.rca.textclassification;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClassificationController {

    private final TextClassifier textClassifier;

    ClassificationController(TextClassifier textClassifier){
        this.textClassifier = textClassifier;
    }

    //Classifying the texts in terms of business, technology or sport without any context.
    @PostMapping("/classify/class-names")
    String classifyClassNames(@RequestBody String text){
        return textClassifier.classifyClassNames(text);
    }

    @PostMapping("/classify/class-descriptions")
    String classifyClassDescriptions(@RequestBody  String text){
        return textClassifier.classifyClassDescriptions(text);
    }

    @PostMapping("/classify/few-shots-prompt")
    String classifyFewShotsPrompt(@RequestBody String text) {
        return textClassifier.classifyFewShotPrompts(text);
    }



}
