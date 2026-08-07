package com.fayetalerror.createarsenal.config;

/**
 * Identifies the broad behavior category of an Arsenal item definition.
 *
 * <p>The enum is intentionally small. A definition describes what an item
 * is, while the corresponding Java item class supplies its behavior.</p>
 */
public enum ItemKind {
    /** A pickaxe, axe, shovel, or hoe-style item. */
    TOOL,

    /** A sword-style weapon. */
    WEAPON,

    /** A wearable armor item. */
    ARMOR,

    /** A multi-purpose tool such as the Paxel. */
    MULTI_TOOL,

    /** A regular item without tool, weapon, or armor behavior. */
    ITEM
}
