import pandas as pd
import matplotlib.pyplot as plt

df = pd.read_csv('nim_game_log.csv')

# Calculating cumulative win rate over time
df['Cumulative Wins'] = df['Result'].cumsum()
df['Games Played'] = df.index + 1
df['Cumulative Win Rate'] = df['Cumulative Wins'] / df['Games Played']

# Calculating win rate by starting pile size
win_rate_by_pile_size = df.groupby('Starting Pile Size')['Result'].mean()

# Calculating win rate based on who starts
win_rate_by_start = df.groupby('Model Starts?')['Result'].mean()

# Overall statistics
total_games = len(df)
total_wins = df['Result'][df['Result'] == 1].count()
total_losses = df['Result'][df['Result'] == -1].count()
overall_win_rate = total_wins / total_games
average_pile_size = df['Starting Pile Size'].mean()

# Plotting
plt.figure(figsize=(18, 6))

# Cumulative Win Rate Over Time
plt.subplot(1, 3, 1)
plt.plot(df['Games Played'], df['Cumulative Win Rate'])
plt.title('Cumulative Win Rate Over Time')
plt.xlabel('Games Played')
plt.ylabel('Cumulative Win Rate')

# Win Rate by Pile Size
plt.subplot(1, 3, 2)
win_rate_by_pile_size.plot(kind='bar')
plt.title('Win Rate by Starting Pile Size')
plt.xlabel('Starting Pile Size')
plt.ylabel('Win Rate')

# Win Rate by Who Starts
plt.subplot(1, 3, 3)
win_rate_by_start.plot(kind='bar', color=['blue', 'orange'])
plt.title('Win Rate Based on Who Starts')
plt.xlabel('Model Starts')
plt.ylabel('Win Rate')
plt.xticks(ticks=[0, 1], labels=['False', 'True'], rotation=0)

plt.tight_layout()
plt.show()

# Chunking data into 50 and graphing
# Chunking data into sets of 50 and calculating the average win rate for each chunk
chunk_size = 50
num_chunks = len(df) // chunk_size
average_win_rates = []

for i in range(num_chunks):
    chunk = df.iloc[i*chunk_size:(i+1)*chunk_size]
    average_win_rate = chunk['Result'].mean()
    average_win_rates.append(average_win_rate)

# Plotting the average win rates for each chunk
plt.figure(figsize=(12, 6))
plt.plot(average_win_rates, marker='o')
plt.title('Average Win Rate in 50-Game Chunks')
plt.xlabel('Chunk Number')
plt.ylabel('Average Win Rate')
plt.grid(True)
plt.show()


# Return insights
print('Total Games:', total_games)
print('Total Wins:', total_wins)
print('Total Losses:', total_losses)
print('Overall Win Rate:', overall_win_rate)