set /p item=Enter item: 

(
echo {
echo	"type": "minecraft:crafting_shaped",
echo	"pattern": [
echo		"###"
echo	],
echo	"key": {
echo		"#": {
echo			"item": "rusted_iron:%item%"
echo		}
echo	},
echo	"result": {
echo		"item": "rusted_iron:%item%_slab",
echo		"count": 6
echo	}
echo }
)>%item%_slab.json

(
echo {
echo	"type": "minecraft:crafting_shaped",
echo	"pattern": [
echo        "#  ",
echo        "## ",
echo		"###"
echo	],
echo	"key": {
echo		"#": {
echo			"item": "rusted_iron:%item%"
echo		}
echo	},
echo	"result": {
echo		"item": "rusted_iron:%item%_stairs",
echo		"count": 4
echo	}
echo }
)>%item%_stairs.json

(
echo {
echo	"type": "minecraft:crafting_shapeless",
echo	"group": "waxed_%item%",
echo	"ingredients": [
echo		{
echo			"item": "rusted_iron:%item%"
echo		},
echo		{
echo			"item": "minecraft:honeycomb"
echo		}
echo	],
echo	"result": {
echo		"item": "rusted_iron:waxed_%item%"
echo	}
echo }
)>waxed_%item%_from_honeycomb.json

(
echo {
echo	"type": "minecraft:crafting_shapeless",
echo	"group": "waxed_%item%",
echo	"ingredients": [
echo		{
echo			"item": "rusted_iron:%item%_slab"
echo		},
echo		{
echo			"item": "minecraft:honeycomb"
echo		}
echo	],
echo	"result": {
echo		"item": "rusted_iron:waxed_%item%_slab"
echo	}
echo }
)>waxed_%item%_slab_from_honeycomb.json

(
echo {
echo	"type": "minecraft:crafting_shapeless",
echo	"group": "waxed_%item%",
echo	"ingredients": [
echo		{
echo			"item": "rusted_iron:%item%_stairs"
echo		},
echo		{
echo			"item": "minecraft:honeycomb"
echo		}
echo	],
echo	"result": {
echo		"item": "rusted_iron:waxed_%item%_stairs"
echo	}
echo }
)>waxed_%item%_stairs_from_honeycomb.json

(
echo {
echo	"type": "minecraft:crafting_shapeless",
echo	"group": "waxed_%item%",
echo	"ingredients": [
echo		{
echo			"item": "rusted_iron:%item%"
echo		}
echo	],
echo	"result": {
echo		"item": "minecraft:iron_ingot",
echo        "count": 9
echo	}
echo }
)>iron_ingot_from_%item%.json

(
echo {
echo	"type": "minecraft:crafting_shapeless",
echo	"group": "waxed_%item%",
echo	"ingredients": [
echo		{
echo			"item": "rusted_iron:waxed_%item%"
echo		}
echo	],
echo	"result": {
echo		"item": "minecraft:iron_ingot",
echo        "count": 9
echo	}
echo }
)>iron_ingot_from_waxed_%item%.json

(
echo {
echo	"type": "minecraft:crafting_shaped",
echo	"pattern": [
echo		"###"
echo	],
echo	"key": {
echo		"#": {
echo			"item": "rusted_iron:waxed_%item%"
echo		}
echo	},
echo	"result": {
echo		"item": "rusted_iron:waxed_%item%_slab",
echo		"count": 6
echo	}
echo }
)>waxed_%item%_slab.json

(
echo {
echo	"type": "minecraft:crafting_shaped",
echo	"pattern": [
echo        "#  ",
echo        "## ",
echo		"###"
echo	],
echo	"key": {
echo		"#": {
echo			"item": "rusted_iron:waxed_%item%"
echo		}
echo	},
echo	"result": {
echo		"item": "rusted_iron:waxed_%item%_stairs",
echo		"count": 4
echo	}
echo }
)>waxed_%item%_stairs.json