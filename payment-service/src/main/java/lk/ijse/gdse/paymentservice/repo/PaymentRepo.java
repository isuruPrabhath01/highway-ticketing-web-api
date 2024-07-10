/**
 * Created By Isuru Prabhath
 * Date : 7/5/2024
 * Time : 9:14 AM
 * Project Name : payment-service
 */

package lk.ijse.gdse.paymentservice.repo;

import lk.ijse.gdse.paymentservice.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepo extends JpaRepository<Payment,String> {
}
