package com.nytimes.android.external.fs;

import com.nytimes.android.external.fs.filesystem.FileSystem;
import com.nytimes.android.external.store.base.RecordProvider;
import com.nytimes.android.external.store.base.RecordState;
import com.nytimes.android.external.store.base.impl.BarCode;

import java.util.concurrent.TimeUnit;

import javax.annotation.Nonnull;
import javax.inject.Inject;

public class RecordPersister extends SourcePersister implements RecordProvider<BarCode> {

    @Nonnull
    private final TimeUnit expirationUnit;
    private final long expirationDuration;

    @Inject
    public RecordPersister(FileSystem fileSystem,
                           @Nonnull TimeUnit expirationUnit,
                           long expirationDuration) {
        super(fileSystem);
        this.expirationDuration = expirationDuration;
        this.expirationUnit = expirationUnit;
    }

    @Override
    public RecordState getRecordState(@Nonnull BarCode barCode) {
        return sourceFileReader.getRecordState(barCode, expirationUnit, expirationDuration);
    }
}
