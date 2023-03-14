package kg.natv.TextAd.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderStatus{
    PAYED("Оплачен"),
    AWAITING("В ожидании оплаты"),
    DECLINED("Отменён");

    private final String status;
}
