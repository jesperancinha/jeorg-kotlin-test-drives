package org.jesperancinha.ktd.crum2

data class AuditResult(
    val roomClass: RoomClass,
    val complianceType: ComplianceType,
    val score:Long
) {
}