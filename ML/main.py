import numpy as np
import tensorflow as tf
import random
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense
from tensorflow.keras.optimizers import Adam

def calculate_optimal_move(pile_size, max_sticks):
    pow = tf.math.floor(tf.math.log(tf.cast(pile_size, tf.float32)) / tf.math.log(2.0))
    ideal_num = tf.math.pow(2.0, pow) - 1
    to_remove = pile_size - tf.cast(tf.round(ideal_num), tf.int32)
    to_remove = tf.minimum(to_remove, max_sticks)
    return to_remove

def custom_loss(y_true, y_pred):
    pile_size = tf.cast(y_true, tf.int32)
    max_sticks = pile_size // 2

    # Calculate the optimal move
    optimal_move = tf.map_fn(lambda x: calculate_optimal_move(x[0], max_sticks), pile_size)

    loss = tf.abs(tf.cast(optimal_move, tf.float32) - y_pred)
    return loss

model = Sequential([
    Dense(128, activation='relu', input_shape=(1,)), # only input value is pile size
    Dense(64, activation='relu'),
    Dense(1, activation='linear')
])

model.compile(optimizer=Adam(learning_rate=0.001), loss=custom_loss)
for episode in range(1000):
    start_pile_size = random.randint(40, 60)
    model.fit(tf.constant([[start_pile_size]], dtype=tf.float32), 
              tf.constant([[start_pile_size]], dtype=tf.float32), epochs=1, verbose=0)

def simulate_game(model, pile_size):
    model_turn = bool(random.getrandbits(1))  # Randomly decide who starts

    while pile_size > 0:
        if model_turn:
            move = model.predict(np.array([[pile_size]]))
            move = int(round(move[0][0]))
            move = max(1, min(move, pile_size, 3))  # Assuming max 3 sticks can be removed
        else:
            move = random.randint(1, min(pile_size, 3))

        pile_size -= move
        model_turn = not model_turn

        if pile_size == 0:
            return not model_turn  # Return True if model wins, False otherwise

# Testing the model
model_wins = 0
for _ in range(50):
    pile_size = random.randint(40, 60)
    if simulate_game(model, pile_size):
        model_wins += 1

print(f"Model won {model_wins} out of 50 games.")

# Display the model's summary
model.summary()
