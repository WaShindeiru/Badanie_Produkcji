class StorageCost:
    def __init__(self):
        self._cost = {
            100: 3000,
            200: 4000,
            300: 6000,
            400: 8000,
            500: 7000,
            600: 8000,
            700: 9000
        }
        
    def getCost(self, storage: int) -> int:
        if storage <= 0:
            return -1
        
        if storage >= max(self._cost.keys()):
            return self._cost[max(self._cost.keys())]
        
        if storage in self._cost.keys():
            return self._cost[storage]
        
        else:
            storage = storage + (100 - storage % 100)
            return self._cost[storage]