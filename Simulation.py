from Optimalisation import Optimalization

if __name__ == "__main__":
    numberOfDays = 10

    expectedProduction = [
        400,
        450,
        300,
        1000,
        100,
        400,
        450,
        600,
        600,
        200
    ]

    scheduledProduction = [
        300,
        500,
        900,
        200,
        550,
        700,
        830,
        200,
        190,
        400
    ]

    optimalization = Optimalization(numberOfDays, scheduledProduction, expectedProduction)

    cost = optimalization.makeSimulation()
    print(cost)
