package study.data_jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;

import java.time.Instant;

@MappedSuperclass
@Getter
public class JpaBaseEntity {

    @Column(updatable = false)
    private Instant createdAt;
    private Instant updatedAt;

    @PreUpdate
    public void preUpdate() {
        updatedAt = Instant.now();
    }

    @PrePersist
    public void prePersist() {
        createdAt = Instant.now();
        updatedAt = createdAt;
    }
}
