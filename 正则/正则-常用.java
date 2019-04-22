
# ipv4
	^((2[0-4]\d|25[0-5]|[01]?\d\d?)\.){3}(2[0-4]\d|25[0-5]|[01]?\d\d?)$

# 移动电话号码
	^1[3-9]\d{9}$


# 用户名
	/^(?!\d+$)(?!_+$)\w{1,14}$/.test(name.replace(/[\u4e00-\u9fa5]/g,"aa"));
	name.replaceAll("[\\u4e00-\\u9fa5]", "aa").matches("^(?!\\d+$)(?!_+$)\\w{1,14}$");

# B站垃圾信息过滤正则(致敬B站的开源精神)
	regexpTaobao    = regexp.MustCompile(`￥([\w\s]+)￥`)
	regexpURL       = regexp.MustCompile(`(?:http|https|www)(?:[\s\.:\/\/]{1,})([\w%+:\s\/\.?=]{1,})`)
	regexpWhitelist = regexp.MustCompile(`((acg|im9|bili|gov).*(com|html|cn|tv)|(av\d{8,}|AV\d{8,}))`)
	regexpQQ        = regexp.MustCompile(`(?:[加qQ企鹅号码\s]{2,}|[群号]{1,})(?:[\x{4e00}-\x{9eff}]*)(?:[:，：]?)([\d\s]{6,})`)
	regexpWechat    = regexp.MustCompile(`(?:[加+微＋+➕薇？vV威卫星♥❤姓xX信]{2,}|weixin|weix)(?:[，❤️.\s]?)(?:[\x{4e00}-\x{9eff}]?)(?:[:，：]?)([\w\s]{6,})`)
