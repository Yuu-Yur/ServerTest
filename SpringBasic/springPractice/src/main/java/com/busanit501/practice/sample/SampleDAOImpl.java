package com.busanit501.practice.sample;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("normal")
public class SampleDAOImpl implements SampleDAO {
    @Override
    public void sampleMethod() {
        System.out.println("Sample Method");
    }
}
