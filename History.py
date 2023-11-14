from IDayIterable import IDayIterable

class History(IDayIterable):
    def __init__(self):
        super().__init__()
        self.trucks = list()
        self.warehouse = list()
        self.productionList = list()
        
        self.dayLimit = len(self.productionList)
        self.currentDay = -1
        
    def getProductionOnDay(self, day: int) -> int:
        if day < self.dayLimit:
            return self.productionList[day]
        
        return -1
    
    def incrementDay(self):
        self.currentDay += 1
        
    def addTransaction(self, truck: int, warehouse: int):
        self.trucks.append(truck)
        self.warehouse.append(warehouse)
        
    # to do: add protection
    def addProduction(self, production: int):
        self.productionList.append(production)
        
    def getProduction(self) -> list[int]:
        return self.productionList
        