
package com.devtrack.backend.model;

public record JobApplication(
        Long id,
        String company,
        String role,
        String status
) {
}
