set /p item=Enter item: 

(
echo {
echo	"type": "minecraft:block",
echo   	"pools": [
echo 		{
echo 			"rolls": 1,
echo 			"bonus_rolls": 0,
echo 			"entries": [
echo 				{
echo 					"type": "minecraft:item",
echo 					"name": "rusted_iron:%item%"
echo 				}
echo 			],
echo 			"conditions": [
echo 				{
echo 					"condition": "minecraft:survives_explosion"
echo 				}
echo 			]
echo 		}
echo	]
echo }
)>%item%.json