import random
import math
import os
import numpy as np
import matplotlib.pyplot as plt

def read_input(file_name="input.txt"):
    file_name = os.path.expanduser("~/Downloads/input.txt")
    with open(file_name, 'r') as f:
        lines = f.readlines()
    N = int(lines[0].strip())  # number of cities
    cities = [tuple(map(int, line.strip().split())) for line in lines[1:N + 1]]  # list of city coordinates
    return np.array(cities)  # Use NumPy array to handle cities as vectors

def CreateInitialPopulation(size, cities):
    population = []
    for _ in range(size):
        path = [cities[0]]
        citiesleft = set(map(tuple, cities[1:]))  # Convert cities to tuple to use with set
        while citiesleft:
            mindist = float('inf')
            citynxt = None
            for city in citiesleft:
                dist = calculate_distance(path[-1], np.array(city))  # Use NumPy array for distance calc
                if dist < mindist:
                    mindist = dist
                    citynxt = city
            path.append(np.array(citynxt))
            citiesleft.remove(citynxt)
        population.append(np.array(path))  # Store as NumPy arrays
    return np.array(population)

def calculate_distance(city1, city2):
    return np.linalg.norm(city1 - city2)  # Use NumPy's vectorized operation for distance

def fitness_function(path):
    total_distance = 0
    for i in range(len(path)):
        total_distance += calculate_distance(path[i], path[(i + 1) % len(path)])  # Loop around the path
    return total_distance

def tournament_selection(population, fitness_scores, tournament_size=3):
    selected = random.sample(list(zip(population, fitness_scores)), tournament_size)
    selected = sorted(selected, key=lambda x: x[1])  # Sort by fitness score
    return selected[0][0], selected[1][0]  # Return the top 2 parents



def order_crossover(parent1, parent2):
    size = len(parent1)
    child = [None] * size
    start, end = sorted(random.sample(range(size), 2))
    child[start:end] = parent1[start:end]
    pointer = end % size
    for city in parent2:
        if not any(np.array_equal(city, c) for c in child if c is not None):
            child[pointer] = city
            pointer = (pointer + 1) % size
    return child


def mutate(child, mutation_rate=0.5):
    for i in range(len(child)):
        if random.random() < mutation_rate:
            j = random.randint(0, len(child) - 1)
            child[i], child[j] = child[j], child[i]
    return np.array(child)

def next_generation(population, fitness_scores, mutation_rate=0.5, es=2):
    new_population = []
    fitpair = list(zip(population, fitness_scores))
    fitpair.sort(key=lambda x: x[1])  # Sort based on fitness score
    best = fitpair[:es]
    new_population.extend([a[0] for a in best])  # Add the best to the new population

    for _ in range((len(population) - es) // 2):
        parents = tournament_selection(population, fitness_scores)
        child1 = order_crossover(parents[0], parents[1])
        child2 = order_crossover(parents[1], parents[0])
        child1 = mutate(child1, mutation_rate)
        child2 = mutate(child2, mutation_rate)
        new_population.append(child1)
        new_population.append(child2)

    return np.array(new_population)

def genetic_algorithim(cities, population_size, generations, mutation_rate):
    population = CreateInitialPopulation(population_size, cities)
    topfit = float('inf')
    toppath = None
    for i in range(generations):
        fitness_scores = [fitness_function(path) for path in population]
        population = next_generation(population, fitness_scores, mutation_rate)
        best_path = min(population, key=fitness_function)
        best_fitness = fitness_function(best_path)
        if best_fitness < topfit:
            topfit = best_fitness
            toppath = best_path

    return toppath, topfit

def visualize_path(cities, best_path, shortest_distance):
    plt.figure(figsize=(8, 6))
    cities = np.array(cities)
    x, y = cities[:, 0], cities[:, 1]
    plt.scatter(x, y, c='red', marker='o')  # Plot cities
    path_x = [city[0] for city in best_path]
    path_y = [city[1] for city in best_path]
    plt.plot(path_x + [path_x[0]], path_y + [path_y[0]], 'b-', label=f'Shortest Distance: {shortest_distance:.3f}')
    plt.xlabel("X Coordinates")
    plt.ylabel("Y Coordinates")
    plt.title("Shortest Path Between Cities")
    plt.legend()
    plt.show()

cities = read_input('input.txt')
best_path, shortest_distance = genetic_algorithim(cities, population_size=10, generations=300, mutation_rate=0.05)

# Save the output
with open('output.txt', 'w') as f:
    f.write(f"{shortest_distance:.3f}\n")
    for city in best_path:
        f.write(f"{' '.join(map(str, city))}\n")
    f.write(f"{' '.join(map(str, best_path[0]))}\n")

# Visualize the result
visualize_path(cities, best_path, shortest_distance)
