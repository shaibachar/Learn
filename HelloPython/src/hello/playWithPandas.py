import pandas as pd
import numpy as np
import matplotlib.pyplot as plt

s = pd.Series([1, 2, 3, 4, np.nan, 8, 9])
print(s)

dates = pd.date_range('20000101', '20000201')
print(dates)

dates = pd.date_range('20000101', periods=6)
df = pd.DataFrame(np.random.randn(6, 4), index=dates, columns=list('ABCD'))
print(df.head())
print(df.tail(2))

df2 = pd.DataFrame({'A': 1.,
                    'B': pd.Timestamp('20130102'),
                    'C': pd.Series(1, index=list(range(4)), dtype='float32'),
                    'D': np.array([3] * 4, dtype='int32'),
                    'E': pd.Categorical(["test", "train", "test", "train"]),
                    'F': 'foo'})

print(df2)
print(df2.dtypes)

print("\n\naggrigate")
df3 = pd.DataFrame({'A': {pd.Timestamp('20130102'): [1,1]},
                    'B': {pd.Timestamp('20130102'): [1,1]}})
df4 = pd.DataFrame({'A': {pd.Timestamp('20130103'): [1,2]},
                    'B': {pd.Timestamp('20130103'): [2,1]}})
dfRes = df3.append(df4)

print(dfRes['A'])
