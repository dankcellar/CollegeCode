#include <stdlib.h>
#include <stdio.h>
#include <strings.h>

struct produceItem
{
    char produce[20];
    char type[20];
    char soldBy[20];
    float price;
    int quantityInStock;
    struct produceItem * next;
};

void addItem(struct produceItem **, char *, char *, char *, float, int);
void readFile(struct produceItem **);
void display(struct produceItem *);
void recursiveReverse(struct produceItem **);
void writeDataFile(struct produceItem *);



int main()
{
    int i;
    struct produceItem * head = NULL;

    while(1)
    {
        printf("List Operations\n");
        printf("===============\n");
        printf("1. Stock Produce Department\n");
        printf("2. Display Produce Inventory\n");
        printf("3. Reverse Order of Produce Inventory\n");
        printf("4. Export Produce Inventory\n");
        printf("5. Exit Program\n");
        printf("Enter your choice: ");

        if(scanf("%d", &i) <= 0)
        {
            printf("Enter only an integer!\n");
            return(0);
        }

        else
        {
            printf("\n");
            switch(i)
            {
                case 1:
                    readFile(&head);
                    break;

                case 2:
                    display(head);
                    break;

                case 3:
                    recursiveReverse(&head);
                    printf("Order reversed!\n\n");
                    break;

                case 4:
                    writeDataFile(head);
                    break;

                case 5:
                    return 0;

                default:
                    printf("Invalid option!\n");
            }
        }
    }
}


void readFile(struct produceItem ** head)
{
    char dataLine[100];
    const char s[2] = ",";
    char * token;
    char * produce;
    char * type;
    char * soldBy;
    float price;
    int quantityInStock;
    char * fileName = "RecitationFiveInput.txt";
    FILE * filePointer = fopen(fileName, "r");;

    printf("Trying to open file %s\n", fileName);
    if(filePointer == NULL)
    {
        perror("Error while opening the file.\n");
        exit(0);
    }
    else
        printf("Successfully opened file %s\n\n", fileName);

    while(fgets(dataLine, 100, filePointer) != NULL)
    {
        printf("%s", dataLine);
        produce = strtok(dataLine, s);
        type = strtok(NULL, s);
        soldBy = strtok(NULL, s);
        price = atof(strtok(NULL, s));
        quantityInStock = atoi(strtok(NULL, s));

        addItem(head, produce, type, soldBy, price, quantityInStock);
    }
    printf("\n\n");
    fclose(filePointer);
}

void addItem(struct produceItem ** head, char * produce, char * type, char * soldBy, float price, int quantity)
{
    struct produceItem * temp, * helper;
    temp = (struct produceItem *) malloc(sizeof(struct produceItem));

    strcpy(temp->produce, produce);
    strcpy(temp->type, type);
    strcpy(temp->soldBy, soldBy);
    temp->price = price;
    temp->quantityInStock = quantity;
    helper = (* head);

    if ((* head) == NULL)
    {
        (* head) = temp;
        (* head)->next = NULL;
    }

    else
    {
        while(helper->next != NULL)
            helper = helper->next;

        temp->next = NULL;
        helper->next = temp;
    }
}

void display(struct produceItem * head)
{
    struct produceItem * temp = head;

    if(temp == NULL)
        return;

    else
    {
        printf("==========================================================================\n");
        printf(" Item #   Produce       Type             Sold By         Price    In Stock\n");
        printf("==========================================================================\n");

        int counter = 1;
        while(temp != NULL)
        {
            printf("%5d %3s %-13s %-16s %-13s %6.2f %8d \n",
                   counter++, " ", temp->produce, temp->type,
                   temp->soldBy, temp->price, temp->quantityInStock);

            temp = temp->next;
        }
    }
    printf("\n");
}

void recursiveReverse(struct produceItem ** head)
{
    struct produceItem * first, * rest;


    if ((* head) == NULL)
       return;

    first = (* head);
    rest  = first->next;

    if (rest == NULL)
       return;

    recursiveReverse(&rest);
    first->next->next = first;
    first->next = NULL;
    (* head) = rest;
}

void writeDataFile(struct produceItem * head)
{
    char * fileName = "RecitationFiveOutput.txt";
    FILE * filePointer;
    int counter = 1;
    struct produceItem * temp = head;


    printf("Trying to create file %s\n", fileName);
    filePointer = fopen(fileName, "w"); // write mode

    if(filePointer == NULL)
    {
        perror("Error while opening the file.\n");
        exit(0);
    }

    if(temp == NULL)
    {
        return;
    }
    else
    {
        fprintf(filePointer, "==========================================================================\n");
        fprintf(filePointer, " Item #   Produce       Type             Sold By         Price    In Stock\n");
        fprintf(filePointer, "==========================================================================\n");

        while(temp != NULL)
        {
            fprintf(filePointer, "%5d %3s %-13s %-16s %-13s %6.2f %8d \n",
                   counter++, " ", temp->produce, temp->type,
                   temp->soldBy, temp->price, temp->quantityInStock);

            temp = temp->next;
        }
    }

    fclose(filePointer);
    printf("Successfully wrote out file %s\n\n", fileName);
}
