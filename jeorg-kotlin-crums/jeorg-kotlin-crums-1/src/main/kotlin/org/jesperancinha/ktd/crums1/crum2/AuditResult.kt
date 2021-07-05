package org.jesperancinha.ktd.crums1.crum2

data class AuditResult(
    val roomClass: RoomClass,
    val complianceType: ComplianceType,
    val score:Long
) {
}