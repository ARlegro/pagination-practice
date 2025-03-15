package study.data_jpa.service;

import java.util.List;

public record ScrollPageResponse<T>(
        List<T> content,
        Object nextCursor,
        int size,  // 현재 페이지에서 반환된 개수
        boolean hasNext,
        Long totalElements) {
}
