/**
 * Created By Isuru Prabhath
 * Date : 7/5/2024
 * Time : 9:14 AM
 * Project Name : payment-service
 */

package lk.ijse.gdse.paymentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {
    private String id;
    private String ticketId;
    private String date;
    private double price;
    private String type;
}
