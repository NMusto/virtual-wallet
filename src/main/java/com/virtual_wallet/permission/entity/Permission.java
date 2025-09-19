package com.virtual_wallet.permission.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "permissions")
@Schema(description = "Data model for the Permission entity")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the permission", example = "123")
    private Long id;

    @Column(nullable = false, unique = true)
    @Schema(description = "Name of the permission", example = "READ_PERMISSIONS")
    private String name;
}
