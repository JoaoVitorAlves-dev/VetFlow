package com.alves.vetflow;

import org.springframework.boot.SpringApplication;

public class TestVetFlowApplication {

    public static void main(String[] args) {
        SpringApplication.from(VetFlowApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
