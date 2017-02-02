#include <stdlib.h>
#include <stdio.h>
#include <strings.h>

struct boggleDataNode
{
    char data[3];
    struct boggleDataNode * nextData;
};

struct boggleDieSideNode
{
    char dieSideData[3];
    struct boggleDieSideNode * nextSide;
};

// defined function prototypes
void readFile(struct boggleDataNode ** dataHead);
void addDataNode(struct boggleDataNode ** dataHead, char * ch);
void displayNode(struct boggleDataNode * dataHead);
void addDieSide(struct boggleDataNode ** dataHead, struct boggleDieSideNode ** dieHead, int counter);
void displayDieSide(struct boggleDieSideNode * dieHead);

int main()
{
    int counter;
    struct boggleDataNode * dataHead = NULL;
    struct boggleDieSideNode * dieHead = NULL;

    readFile(&dataHead);
    displayNode(dataHead);

    // counter used as an index to insure the correct
    // data is feed through the function
    for(counter = 0; counter < 96; counter++)
    {
        addDieSide(&dataHead, &dieHead, counter);
    }

    displayDieSide(dieHead);
    return 0;
}


// opens, reads, and writes the string of characters into
// the addDataNode function to store as memory
void readFile(struct boggleDataNode ** dataHead)
{
    char * ch;
    FILE * fp = fopen("BoggleData.txt", "r");

    // close program if .txt file isn't found
    if (fp == NULL)
        exit(0);

    // scan data file for strings
    while ((fscanf(fp, "%s", ch)) != EOF)
        addDataNode(dataHead, ch);

    fclose(fp);
}

// allocates memory for each node and adds it to the
// end of the linked list
void addDataNode(struct boggleDataNode ** dataHead, char * ch)
{
    struct boggleDataNode * temp, * helper;
    temp = (struct boggleDataNode *) malloc(sizeof(struct boggleDataNode));
    strcpy(temp->data, ch);         // sets the data of the first node
    helper = (*dataHead);

    // add the first node if the linked list is empty
    if ((*dataHead) == NULL)
    {
        (*dataHead) = temp;
        (*dataHead)->nextData = NULL;
    }

    else
    {
        // traverses the linked list for the end
        while(helper->nextData != NULL)
            helper = helper->nextData;

        temp->nextData = NULL;      // set the next node as NULL
        helper->nextData = temp;    // prepare to assign next data value
    }
}

void displayNode(struct boggleDataNode * dataHead)
{
    int i = 0;
    struct boggleDataNode * temp = dataHead;

    printf("**** Displaying Boggle Data ****\n");
    while(temp != NULL)
    {
        // while data is found; print to screen
        printf("Data value %d : %s\n", i++, temp->data);
        temp = temp->nextData;
    }

    printf("Press the [Enter] key to continue . . . \n");
    getchar();
}

void addDieSide(struct boggleDataNode ** dataHead, struct boggleDieSideNode ** dieHead, int counter)
{
    struct boggleDieSideNode * temp, * helper;
    temp = (struct boggleDieSideNode *) malloc(sizeof(struct boggleDieSideNode));
    strcpy(temp->dieSideData, (*dataHead)->data);       // sets first indexed side to the first data node
    helper = (*dieHead);

    // checks for accurate index
    if(counter <96)
    {

        // creates a die header if list is empty
        if((*dieHead) == NULL)
        {
            (*dieHead) = temp;
            (*dieHead)->nextSide = NULL;
            (*dataHead) = (*dataHead)->nextData;
        }

        else
        {
            // traverse linked list to find the end
            while(helper->nextSide != NULL)
                helper = helper->nextSide;

            (*dataHead) = (*dataHead)->nextData;
            temp->nextSide = NULL;
            helper->nextSide = temp;
        }
    }
    else
        return;
}

void displayDieSide(struct boggleDieSideNode * dieHead)
{
    int side;
    struct boggleDieSideNode * temp = dieHead;

    while(temp != NULL)
    {
        // counts the side of the die
        for(side = 0; side < 6; side++)
        {
            if(side == 0)
                printf("**** Display Die Side Data ****\n");

            // prints the side of each die for all 16 die
            printf("Side %d : %s\n", side, temp->dieSideData);
            temp = temp->nextSide;
        }

    printf("Press the [Enter] key to continue . . . \n");
    getchar();
    }
}

