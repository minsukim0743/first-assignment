package com.furence.assignment.user.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class InsertData {

    private Map<Integer, String> map;
    private AtomicInteger successCount;
    private AtomicInteger failCount;

    public InsertData() {}

    public InsertData(Map<Integer, String> map, AtomicInteger successCount, AtomicInteger failCount) {
        this.map = map;
        this.successCount = successCount;
        this.failCount = failCount;
    }

    public Map<Integer, String> getMap() {
        return map;
    }

    public void setMap(Map<Integer, String> map) {
        this.map = map;
    }

    public AtomicInteger getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(AtomicInteger successCount) {
        this.successCount = successCount;
    }

    public AtomicInteger getFailCount() {
        return failCount;
    }

    public void setFailCount(AtomicInteger failCount) {
        this.failCount = failCount;
    }

    @Override
    public String toString() {
        return map.toString();

    }
}
