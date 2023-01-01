package com.pcc.carrental.model.enums;


/* Flow of status:
 *
 *  CREATED -→ PAID -→ RENTED -→ RETURNED
 *     ↓        ↓
 * CANCELLED CANCELLED
 *
 */
public enum RentalTransactionStatus {
    CREATED,
    CANCELLED,
    PAID,
    RENTED,
    RETURNED
}
