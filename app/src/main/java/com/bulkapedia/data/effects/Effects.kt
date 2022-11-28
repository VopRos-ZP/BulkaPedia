package com.bulkapedia.data.effects

import com.bulkapedia.R

enum class Effects(val id: Int) {
    DAMAGE(R.string.damage_effect),
    ARMOR_DAMAGE(R.string.armor_damage_effect),
    HEALTH_DAMAGE(R.string.health_damage_effect),
    MAX_HEALTH(R.string.max_health_effect),
    MAX_ARMOR(R.string.max_armor_effect),
    ADD_HEALTH(R.string.add_health_effect),
    ADD_ARMOR(R.string.add_armor_effect),
    SPEED(R.string.speed_effect),
    SPEED_IN_FOCUS(R.string.speed_in_focus_effect),
    SPREAD_IN_MOVE(R.string.spread_in_move_effect),
    SPREAD_IN_FOCUS(R.string.spread_in_focus_effect),
    SPREAD_IN_NOT_FOCUS(R.string.spread_in_not_focus_effect),
    VISIBILITY(R.string.visibility_effect),
    PIERCING(R.string.piercing_effect),
    FIRE_RATE(R.string.fire_rate_effect),
    FIRE_RANGE_IN_FOCUS(R.string.fire_range_focus_effect),
    FIRE_RANGE(R.string.fire_range_effect),
    AIMING_SPEED(R.string.aiming_speed_effect),
    RUNNING_VOLUME(R.string.running_volume_effect),
    TIME_UP_AMMO(R.string.time_up_ammo_effect),
    TIME_UP_BUST(R.string.time_up_bust_effect),
    RECHARGE_RATE(R.string.reloading_time_effect),
    BREAKING_WALLS(R.string.piercing_power_effect),
    ADD_PATRONS(R.string.add_patrons_effect),

    /** персональные бафы **/
    /** ракеты/гранаты **/
    MAX_GRENADE_DAMAGE(R.string.max_damage_grenade_effect),
    MAX_GRENADE_RADIUS(R.string.max_radius_grenade_effect),
    MAX_ROCKET_DAMAGE(R.string.max_damage_rocket_effect),
    MAX_ROCKET_RADIUS(R.string.max_radius_rocket_effect),
    /** прыжки/инвиз **/
    JUMP_DISTANCE(R.string.jump_distance_effect),
    JUMP_DAMAGE(R.string.jump_damage_effect),
    SPEED_AFTER_JUMP(R.string.speed_after_jump_effect),
    INVISIBLE_DURATION(R.string.invisible_duration_effect),
    DAMAGE_AFTER_INVISIBLE(R.string.invisible_damage_effect),
    SPEED_AFTER_INVISIBLE(R.string.speed_after_invisible_effect),
    /** просветка/тепловизор **/
    SCAN_RADIUS(R.string.scan_radius_effect),
    SCAN_DAMAGE(R.string.scan_damage_effect),
    SCAN_DURATION(R.string.scan_duration_effect),
    VISION_DURATION(R.string.vision_duration_effect),
    BREAKING_WALL_IN_VISION(R.string.piercing_vision_effect),
    /** щит/стенка **/
    WALL_DURATION(R.string.wall_duration_effect),
    SHIELD_DURATION(R.string.shield_duration_effect),
    WALL_PROTECTION(R.string.wall_protection_effect),
    SHIELD_PROTECTION(R.string.shield_protection_effect),
    FIRE_RANGE_IN_ACTIVE_WALL(R.string.fire_range_while_wall_effect),
    /** турель/подавление **/
    TURRET_DAMAGE(R.string.turret_damage_effect),
    HEALTH_ARMOR_TURRETS(R.string.turret_health_effect),
    BREAKING_WALL_ON_SUPPRESSION(R.string.piercing_on_suppression_effect),
    RECHARGE_RATE_AFTER_SUPPRESSION(R.string.reloading_after_suppression_effect),
    /** бафы хилок **/
    /** дробовики **/
    TEAM_HEALTH_UP(R.string.team_health_up_effect),
    HEALTH_AFTER_TEAM_HEALTH(R.string.health_after_team_health_effect),
    /** разведчики **/
    STIMULATOR_HEALTH(R.string.stimulator_health_effect),
    SPEED_AFTER_STIMULATOR(R.string.stimulator_speed_effect),
    /** снайперы **/
    BAND_RUNNING_VOLUME(R.string.band_running_volume_effect),
    BAND_HEALTH_UP(R.string.band_health_up_effect),
    /** танки **/
    MAX_HEALTH_AFTER_HEAL(R.string.max_health_after_tank_heal_effect),
    TANK_HEALTH_ON_ACTIVATE(R.string.tank_heal_on_activate_effect),
    /** штрмуовики **/
    BUFF_DAMAGE_ON_ACTIVATE_TROOPER(R.string.buff_damage_trooper_effect),
    BUFF_HEALTH_ON_ACTIVATE_TROOPER(R.string.buff_health_trooper_effect),
    ;
}