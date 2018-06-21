{
	"query":{

		"match_all":{
			
		},

		"match":{
			"key":"value"
		},

		"match_phrase":{
			"key":"value"
		}

		"bool":{
			"must":{
				"match":{
					"key":"value"
				}
			},
			"filter":{
				"range":{
					"key":{"gt/lt/le/ge/ne":"value"}
				}
			}
		}
	},
	"sort":[
		{"key1":"desc"},
		{"key2":"asc"}
	],
	"from":1,
	"size":2,
	"_source":[],
	"highlight":{
		"fields":{
			"key":{}
		}
	}
}

query.match_all
	* 检索所有,该值是一个空对象{}

query.match
	* 检索符合条件的
	* 该值是一个对象,通过kv来组合条件
	* 全文检索,value可以有多个,使用空格隔开,只要key中包含了任意value关键字即满足条件

query.match_phrase
	* 短语检索,跟全文检索相反,必须是全部符合key=value,才符合条件

query.bool
queyr.boot.must.match

from
	* 从第几个数据开始检索,limit的第一个参数
size
	* 每页显示的记录数
_source
	* 该值是一个数组,指定要检索的字段,而不是检索所有
	* 通过字符串执行属性,支持.属性导航

highlight.fields
	* 高亮设置