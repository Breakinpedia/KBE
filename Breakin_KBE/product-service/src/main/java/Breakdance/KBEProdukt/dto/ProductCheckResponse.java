package Breakdance.KBEProdukt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductCheckResponse {
    private String id;
    private boolean isInProduct;
    private String name;
}
