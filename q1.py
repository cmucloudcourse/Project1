import pandas as pd
import sys
from csv import QUOTE_NONE
df = pd.read_table('output', index_col=1, encoding='utf-8', sep='\t', header=None)
df.describe().to_csv(sys.stdout, encoding='utf-8', float_format='%.2f')
