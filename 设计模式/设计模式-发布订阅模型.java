----------------
·¢²¼¶©ÔÄ		|
----------------

let publisher = (function(){
	let queue = {}
	function sub(event, listner){
		if (!queue[event]){
			queue[event] = []
		}
		queue[event].push(listner)
	}
	function push(event, resource){
		if (queue[event]){
			for (let listner of queue[event]){
				listner(resource)
			}
		}
	}
	return {
		sub,push
	}
})();

publisher.sub('foo', data => {
	console.log(data);
});
publisher.sub('foo', data => {
	console.log(data);
});

publisher.sub('foo', data => {
	console.log(data);
});


publisher.push('foo', 'Hello World');
publisher.push('bar', 'Hello World');