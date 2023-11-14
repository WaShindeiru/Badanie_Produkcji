from Production import Production

class ProductionCost:
    def __init__(self):
        self._production = {
            100: 10000,
            200: 19000,
            300: 27000,
            400: 35000,
            500: 41000,
            600: 50000,
            700: 54000
        }
        
    def getProduction(self, quantity: int) -> Production:
        if quantity <= 0:
            raise Exception
        
        if quantity >= max(self._production.keys()):
            quantity = max(self._production.keys())
            return Production(quantity, self._production[quantity])
        
        if quantity in self._production:
            return Production(quantity, self._production[quantity])
        
        else:
            quantity = quantity + (100 - quantity % 100)
            return Production(quantity, self._production[quantity])
            
    def productionMax(self) -> int:
        return max(self._production.values())