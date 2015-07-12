#include <cstdio>

const int N = 110;

int a[N], c[N][N], n, k, m, i, j;

int main()
{
    scanf("%d%d", &n, &k);

    m = N;
    for (i = 0; i < n; i++)
    {
        scanf("%d", &a[i]);
        if (a[i] < m)
            m = a[i];
    }
    ++m;

    for (i = 0; i < n; i++)
    {
        int t = 1;
        for (j = 0; j < a[i]; j++)
        {
            if (j < m)
                c[i][j] = 1;
            else
                c[i][j] = ++t;
            if (c[i][j] > k)
            {
                puts("NO");
                return 0;
            }
        }
    }

    puts("YES");
    for (i = 0; i < n; i++)
    {
        for (j = 0; j < a[i]; j++)
            printf("%d ", c[i][j]);
        printf("\n");
    }

    return 0;
}