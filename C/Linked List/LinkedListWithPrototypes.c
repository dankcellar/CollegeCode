#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct node
{
    char name[50];
    struct node *next;
}*head;

// function prototypes
void append(char string[]);
int removenode(char string[]);
void display(struct node * j);
int count();

// main function
int main()
{
    char string[50];
    int i;
    struct node * nd;
    head = NULL;

    // prompts user with valid inputs and
    // loops function until invalid input or
    // user enters integer 5
    while(1)
    {
        printf("1.Insert\n");
        printf("2.Display\n");
        printf("3.Count\n");
        printf("4.Delete\n");
        printf("5.Exit\n");
        printf("Enter your choice: ");

        if(scanf("%d", &i) <= 0)
        {
            printf("Enter only an integer!\n");
            return(0);
        }

        else
        {

            // gets input and switches to the case
            switch(i)
            {
                // insert name
                case 1:
                    printf("Enter a name to insert : ");
                    scanf("%s", &string);
                    append(string);
                    printf("\n");
                    break;

                // display names
                case 2:
                    if(head == NULL)
                        printf("List is empty!\n\n");
                    else
                        printf("Name(s) in the list are: ");

                    // pointer nd used as placeholder for all nodes
                    display(nd);
                    break;

                // count the number of names in the list
                case 3:
                    printf("Amount of names in the list is %d!\n\n", count());
                    break;

                // delete a name
                case 4:
                    if(head == NULL)
                        printf("Nothing to delete!\n\n");
                    else
                    {
                        printf("Enter a name to delete: ");
                        scanf("%s", &string);

                        // if can be deleted otherwise else
                        if(removenode(string))
                            printf("%s deleted successfully!\n\n", string);
                        else
                            printf("%s not found in the list!\n\n", string);
                    }
                    break;

                // exit program
                case 5:
                    return 0;

                // set default in case of invalid input
                default:
                    printf("Invalid option!\n\n");
            }
        }
    }

    return 0;
}

// function definitions
// adding a node to the beginning of the linked list (stack)
void append(char string[])
{
    struct node *temp;
    temp =(struct node *)malloc(sizeof(struct node));
    strcpy(temp->name, string);

    // first node in the linked list
    if (head == NULL)
    {
        head = temp;
        head->next = NULL;
    }

    else
    {
        temp->next = head;
        head = temp;
    }
}

// deletes a name within the list
int removenode(char string[])
{
    struct node *temp, *prev;
    temp = head;

    // finds a node with the correct name to delete
    while(temp != NULL)
    {
        // compares strings
        if(strcmp(temp->name, string) == 0)
        {
            if(temp == head)
            {
                head = temp->next;
                head = (*temp).next;
                free(temp);
                return 1;
            }
            else
            {
                prev->next = temp->next;
                free(temp);
                return 1;
            }
        }
        else    // moves to previous node if correct one is not found
        {
            prev = temp;
            temp = temp->next;
        }
    }
    return 0;
}

// displays the names in the list until
// a NULL node is found
void display(struct node * j)
{
    j = head;
    while(j != NULL)
    {
        printf("%s ", j->name);
        j = j->next;
    }

    printf("\n\n");
}

// counts the amount of names in the list
int count()
{
    struct node * temp;
    int c = 0;
    temp = head;

    // counts 1 up for each node
    while(temp != NULL)
    {
        temp = temp->next;
        c++;
    }
    return c;
}
