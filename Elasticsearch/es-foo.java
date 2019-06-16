GET /javaweb/_search
{
	"query":{
		"bool":{
			"should":[{
				"math":{
					"user_id":1001
				}
			},{
				"match":{
					"user_id":1002
				}
			}]
		}
	},
	"_source":["title", "user_id", "autor.name"]
}