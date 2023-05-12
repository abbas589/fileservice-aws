package com.bazztech.fileservice.domain.entity;

import com.bazztech.fileservice.service.SequenceGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author bazz
 * May 07 2023
 * 12:59
 */

@Service
@RequiredArgsConstructor
public class FileListener extends AbstractMongoEventListener<File> {
    private final SequenceGeneratorService sequenceGenerator;
    @Override
    public void onBeforeConvert(BeforeConvertEvent<File> event) {
        if (Objects.isNull(event.getSource().getId())|| event.getSource().getId() < 1) {
            event.getSource().setId(sequenceGenerator.generateSequence(File.SEQUENCE_NAME));
        }
    }
}
