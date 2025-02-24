package vn.com.ktxcomay.cmd.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

/**
 * Abstract superclass for common metadata fields.
 */
@Data
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long createdBy;

    private Long modifiedBy;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;
}
