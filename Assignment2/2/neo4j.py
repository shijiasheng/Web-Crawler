from neo4j import GraphDatabase, basic_auth
driver = GraphDatabase.driver("bolt://localhost:7687", auth=basic_auth("neo4j", "123"))

def get(tx, lis):
    result = tx.run("match(m:movie)<-[r:review]-(n:user) where m.movieId in $lis return distinct(n.userId)", lis=lis)
    res = []
    for i in result:
        res.append(i)
    return res
max_length = 0
with driver.session() as session:
    for line in open('./res.txt', 'r'):
        lis = line.replace('\n', '').split(',')[:-1]
        res = session.write_transaction(get, lis)
        length = 0 if (res is None) else len(session.write_transaction(get, lis))
        if (length > max_length):
            max_length = length
print(max_length)
print("===================finish===================")
