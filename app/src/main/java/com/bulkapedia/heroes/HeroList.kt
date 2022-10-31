package com.bulkapedia.heroes

import com.bulkapedia.heroes.scouts.*
import com.bulkapedia.heroes.shortguns.*
import com.bulkapedia.heroes.snipers.*
import com.bulkapedia.heroes.tanks.*
import com.bulkapedia.heroes.troopers.*

class HeroList {
    companion object {
        val ARNIE = Arnie()
        val CYCLOPS = Cyclops()
        val SPARKLE = Sparkle()
        val HURRICANE = Hurricane()
        val GHOST = Ghost()
        val FREDDIE = Freddie()
        val ANGEL = Angel()
        val RAVEN = Raven()
        val BLOT = Blot()
        val FIREFLY = Firefly()
        val SLAYER = Slayer()
        val MIRAGE = Mirage()
        val LYNX = Lynx()
        val SMOG = Smog()
        val BASTION = Bastion()
        val DRAGOON = Dragoon()
        val BERTHA = Bertha()
        val LEVIATHAN = Leviathan()
        val STALKER = Stalker()
        val DOC = Doc()
        val LEVI = Levi()
        val SATOSHI = Satoshi()

        val SHOTGUNS = listOf(ARNIE, CYCLOPS, SPARKLE, HURRICANE)
        val SCOUTS = listOf(GHOST, FREDDIE, ANGEL, RAVEN)
        val SNIPERS = listOf(BLOT, FIREFLY, SLAYER, LYNX, MIRAGE)
        val TANKS = listOf(SMOG, BASTION, DRAGOON, BERTHA, LEVIATHAN)
        val TROOPERS = listOf(STALKER, DOC, LEVI, SATOSHI)

        val HEROES = SHOTGUNS + SCOUTS + SNIPERS + TANKS + TROOPERS

        fun getHeroByBigImage(image: Int) : Hero = HEROES.filter { it.getBigIcon() == image }[0]

    }
}