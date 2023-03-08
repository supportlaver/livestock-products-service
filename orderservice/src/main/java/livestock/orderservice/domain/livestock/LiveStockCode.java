package livestock.orderservice.domain.livestock;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LiveStockCode {
    private String code;
    private String displayName;

    public LiveStockCode(String code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }
}
