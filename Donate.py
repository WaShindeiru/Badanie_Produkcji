import History

class Donate:
    def __init__(self, history: History):
        self.history = history
        self.donate = 2000
        
    def getDonate(self) -> int:
        productionList = self.history.getProduction()
        count = 0
        
        for j in range(len(productionList) - 1):
            if productionList[j] < productionList[j + 1]:
                count += 1
                
        return self.donate * count