#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

#define NAME_SIZE 20

#define MAX_LIST_SIZE 100

struct Item {
	int id;
	char name[NAME_SIZE];
};

struct Node {
	struct Item * item;
	struct Node * next;
};

struct List {
	struct Node * head;
	int size;
};

extern struct Node * createNode(struct Item *);
extern struct Item * createItem(int, char *);
extern bool addItem(struct List *, struct Item *);
extern void consumer(struct List *, void (*)(void *));
extern void console(void *);

int main(int argc, char **argv) {

	//创建了一个链表
	struct List list = { NULL, 0 };

	//添加第一个节点数据
	addItem(&list, createItem(1, "KevinBlandy"));
	addItem(&list, createItem(2, "Litch"));
	addItem(&list, createItem(3, "Rocco"));

	consumer(&list, &console);

	return EXIT_SUCCESS;
}

//添加数据到链表
bool addItem(struct List *list, struct Item *item) {
	int size = list->size;

	//创建首节点
	if (size == 0) {
		struct Node * node = createNode(item);

		list->head = node;
		list->size = 1;
		return true;
	} else if (size >= MAX_LIST_SIZE) {
		//超出最大长度
		return false;
	}

	//创建子链节点
	struct Node * node = createNode(item);

	list->head[(list->size) - 1].next = node;
	list->size += 1;
	return true;
}

//创建节点
struct Node * createNode(struct Item *item) {
	struct Node * node = calloc(1, sizeof(struct Node));
	node->item = item;
	node->next = NULL;
	return node;
}

//创建item
struct Item * createItem(int id, char *name) {
	struct Item * item = calloc(1, sizeof(struct Item));
	item->id = id;
	strncpy(item->name, name, NAME_SIZE - 1);
	return item;
}

//对所有节点进行处理
void consumer(struct List *list, void (*consumer)(void *)) {
	struct Node * node = list->head;
	if (node != NULL) {
		consumer(node->item);
		node = node->next;
	}
}

//打印一个item
void console(void *valule) {
	struct Item * item = ((struct Item *) valule);
	printf("id=%d,name=%s\n", item->id, item->name);
}
