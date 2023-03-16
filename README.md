Финальныый проэкт курса Java Bootcamp
Бэкенд сайта natv.kg

# #GET /API/V1/CHANNEL/LIST - ПОЛУЧЕНИЕ СПИСКА КАНАЛОВ

#Условия:
#	1. Вернуть только активные каналы
#	2. Сортивка по названию канала
#	3. url картинки можно взять из google

[
	{
		"channelName":"MTV",
		"logo":"http://ru.nts.kg/wp-content/uploads/2019/07/Logo-NTS-2.jpg",
		"pricePerLetter":10,
		"discounts":[
			{
				"fromDayCount":3,
				"discount":5
			},
			{
				"fromDayCount":5,
				"discount":10
			},
		]
		
	},
	{
		"channelName":"НТС",
		"logo":"var/dev/logos/mtv.png",
		"pricePerLetter":10,
		"discounts":[
		]
		
	}
	{...}

]


#GET /api/v1/channel/calculate - Получение стоимости рекламы на одном канале
#
#Условия:
#	1. Если есть скидки, то необходимо применить
#	2. Если канал не активен, то вернуть ошибку
#	3. Стоимость рекламы:
#		а. Пробелы не считаем
#		b. Минимум 20 символов

Request:
{
	"text":"skjdfnksdfnj s  .",
	"daysCount":4,
	"channelId":3
}

Response:
{
	"text":"skjdfnksdfnj s .",
	"daysCount":4,
	"channelId":3,
	"price":141,
	"priceWithDiscount":100
}



#POST /api/v1/order/save - Создание заявки на рекламу
#
#Request:

{
	"clientEmail":"mymail@mail.com",
	"clientFIO":"Test test",
	"clientPhone":"+003885740334",
	"text":"skdjnfks",
	"channels":[
		{
			"channelId":1,
			"days":["12.12.2022", "13.12.2023"]
		},
		{
			"channelId":2,
			"days":["12.12.2022", "13.12.2023"]
		}
	]
}


#RESPONSE:
{
	"clientEmail":"mymail@mail.com",
	"clientFIO":"Test test",
	"clientPhone":"+003885740334",
	"text":"skdjnfks",
	"totalPrice":24,
	"status":"CREATED",
	"channels":[
		{
			"channelId":1,
			"days":["12.12.2022", "13.12.2023"],
			"price":12,
			"priceWithDiscount":10
		},
		{
			"channelId":2,
			"days":["12.12.2022", "13.12.2023"],
			"price":12,
			"priceWithDiscount":10
		}
	]
}
