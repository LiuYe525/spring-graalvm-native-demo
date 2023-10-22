package com.lye.nativeimagedemo;

import io.netty.buffer.AbstractByteBufAllocator;
import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportRuntimeHints;

@SpringBootApplication
@ImportRuntimeHints(DemoApplicationRuntimeHints.class)
public class NativeImageDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(NativeImageDemoApplication.class, args);
    }

}

class DemoApplicationRuntimeHints implements RuntimeHintsRegistrar {

    @Override
    public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
        hints.reflection().registerType(AbstractByteBufAllocator.class, MemberCategory.INVOKE_DECLARED_METHODS);
    }

}