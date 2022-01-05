import turtle

class Shape():
    def makeSliver(self, xOuterRadius, yOuterRadius, xOuterCenter, yOuterCenter,
                   xInnerRadius, yInnerRadius, xInnerCenter, yInnerCenter, Q1, Q2, Q3, Q4, red, green, blue):
        turtle.colormode(255)
        turtle.color((red, green, blue))
        turtle.left(90)
        turtle.speed(0)
        for x in range(-xOuterRadius + xOuterCenter, xOuterRadius + xOuterCenter + 1):
            print("x: ", x)
            if Q1:
                if xOuterCenter <= x <= xOuterRadius + xOuterCenter:  #if x is in the domain of Q1 for the outer ellipse
                    print("\tQ1")
                    yTopOuter = yOuterRadius * (1 - ((x - xOuterCenter) / xOuterRadius) ** 2) ** .5
                    yBotOuter = -yOuterRadius * (1 - ((x - xOuterCenter) / xOuterRadius) ** 2) ** .5
                    yTopInner = yInnerRadius * (1 - ((x - xInnerCenter) / xInnerRadius) ** 2) ** .5
                    yBotInner = -yInnerRadius * (1 - ((x - xInnerCenter) / xInnerRadius) ** 2) ** .5
                    print("yTopOuter, yBotOuter, yTopInner, yBotInner: ", yTopOuter, yBotOuter, yTopInner, yBotInner)
                    if -xInnerRadius + xInnerCenter <= x <= xInnerRadius + xInnerCenter:
                        if yOuterCenter <= (yTopInner + yInnerCenter) <= (yTopOuter + yOuterCenter):  # if inner ellipse is completely inside outer ellipse
                            print("top inner is below top outer and above outer center")
                            turtle.penup()
                            turtle.setpos(x, yTopOuter)
                            turtle.pendown()
                            turtle.forward((yTopInner + yInnerCenter) - (yTopOuter + yOuterCenter))
                            turtle.penup()
                        elif yTopInner + yInnerCenter >= yTopOuter + yOuterCenter and yBotInner + yInnerCenter >= yTopOuter + yOuterCenter:
                            print("top and bot inner ellipse are above top outer ellipse")
                            turtle.penup()
                            turtle.setpos(x, yOuterCenter)
                            turtle.pendown()
                            turtle.forward(yTopOuter)
                            turtle.penup()
                        elif yTopInner + yInnerCenter > yTopOuter + yOuterCenter and yBotInner + yInnerCenter < yBotOuter + yOuterCenter:
                            print("inner ellipse contains the outer ellipse")
                        elif yTopInner + yInnerCenter <= yOuterCenter and yBotInner + yInnerCenter <= yOuterCenter:
                            print("top and bot inner are below outer center")
                            turtle.penup()
                            turtle.setpos(x, yOuterCenter)
                            turtle.pendown()
                            turtle.forward(yTopOuter)
                            turtle.penup()
                        if yOuterCenter <= yBotInner + yInnerCenter <= yTopOuter + yOuterCenter:  # if inner ellipse is completely inside outer ellipse
                            print("bot inner is below top outer and above outer center")
                            turtle.penup()
                            turtle.setpos(x, yOuterCenter)
                            turtle.pendown()
                            turtle.forward((yBotInner + yInnerCenter) - yOuterCenter)
                            turtle.penup()
                    else:
                        print("inner ellipse's domain does not contain this x value")
                        turtle.penup()
                        turtle.setpos(x, yOuterCenter)
                        turtle.pendown()
                        turtle.forward(yTopOuter)
                        turtle.penup()


            if Q2:
                if -xOuterRadius + xOuterCenter <= x <= xOuterCenter:  #if x is in the domain of Q2
                    print("\tQ2")
                    yTopOuter = yOuterRadius * (1 - ((x - xOuterCenter) / xOuterRadius) ** 2) ** .5
                    yBotOuter = -yOuterRadius * (1 - ((x - xOuterCenter) / xOuterRadius) ** 2) ** .5
                    yTopInner = yInnerRadius * (1 - ((x - xInnerCenter) / xInnerRadius) ** 2) ** .5
                    yBotInner = -yInnerRadius * (1 - ((x - xInnerCenter) / xInnerRadius) ** 2) ** .5
                    print("yTopOuter, yBotOuter, yTopInner, yBotInner: ", yTopOuter, yBotOuter, yTopInner, yBotInner)
                    if -xInnerRadius + xInnerCenter <= x <= xInnerRadius + xInnerCenter:
                        if yOuterCenter <= (yTopInner + yInnerCenter) <= (yTopOuter + yOuterCenter):  # if inner ellipse is inside outer ellipse for Q2
                            print("top inner is below top outer and above outer center")
                            turtle.penup()
                            turtle.setpos(x, yTopOuter)
                            turtle.pendown()
                            turtle.forward((yTopInner + yInnerCenter) - (yTopOuter + yOuterCenter))
                            turtle.penup()
                        elif yTopInner + yInnerCenter >= yTopOuter + yOuterCenter and yBotInner + yInnerCenter >= yTopOuter + yOuterCenter:
                            print("top and bot inner are above top outer")
                            turtle.penup()
                            turtle.setpos(x, yOuterCenter)
                            turtle.pendown()
                            turtle.forward(yTopOuter)
                            turtle.penup()
                        elif yTopInner + yInnerCenter > yTopOuter + yOuterCenter and yBotInner + yInnerCenter < yBotOuter + yOuterCenter:
                            print("inner ellipse contains the outer ellipse")
                        elif yTopInner + yInnerCenter <= yOuterCenter and yBotInner + yInnerCenter <= yOuterCenter:
                            print("top and bot inner are below outer center")
                            turtle.penup()
                            turtle.setpos(x, yOuterCenter)
                            turtle.pendown()
                            turtle.forward(yTopOuter)
                            turtle.penup()
                        if yOuterCenter <= yBotInner + yInnerCenter <= yTopOuter + yOuterCenter:  # if inner ellipse is completely inside outer ellipse
                            print("bot inner is below top outer and above outer center")
                            turtle.penup()
                            turtle.setpos(x, yOuterCenter)
                            turtle.pendown()
                            turtle.forward((yBotInner + yInnerCenter) - yOuterCenter)
                            turtle.penup()
                    else:
                        print("inner ellipse's domain does not contain this x value")
                        turtle.penup()
                        turtle.setpos(x, yOuterCenter)
                        turtle.pendown()
                        turtle.forward(yTopOuter)
                        turtle.penup()

            if Q3:
                if -xOuterRadius + xOuterCenter <= x <= xOuterCenter:  # if x is in the domain of Q3
                    print("\tQ3")
                    yTopOuter = yOuterRadius * (1 - ((x - xOuterCenter) / xOuterRadius) ** 2) ** .5
                    yBotOuter = -yOuterRadius * (1 - ((x - xOuterCenter) / xOuterRadius) ** 2) ** .5
                    yTopInner = yInnerRadius * (1 - ((x - xInnerCenter) / xInnerRadius) ** 2) ** .5
                    yBotInner = -yInnerRadius * (1 - ((x - xInnerCenter) / xInnerRadius) ** 2) ** .5
                    print("yTopOuter, yBotOuter, yTopInner, yBotInner: ", yTopOuter, yBotOuter, yTopInner, yBotInner)
                    if -xInnerRadius + xInnerCenter <= x <= xInnerRadius + xInnerCenter:
                        if yBotOuter + yOuterCenter <= yBotInner + yInnerCenter <= yOuterCenter:
                            print("bot inner is below outer center and above bot outer")
                            turtle.penup()
                            turtle.setpos(x, yBotOuter)
                            turtle.pendown()
                            turtle.forward((yBotInner + yInnerCenter) - (yBotOuter + yOuterCenter))
                            turtle.penup()
                        elif yTopInner + yInnerCenter <= yBotOuter + yOuterCenter and yBotInner + yInnerCenter <= yBotOuter + yOuterCenter:
                            print("top and bot inner are below bot outer")
                            turtle.penup()
                            turtle.setpos(x, yOuterCenter)
                            turtle.pendown()
                            turtle.forward(yBotOuter)
                            turtle.penup()
                        elif yTopInner + yInnerCenter > yOuterCenter and yBotInner + yInnerCenter > yOuterCenter:
                            print("top and bot inner are above outer center")
                            turtle.penup()
                            turtle.setpos(x, yOuterCenter)
                            turtle.pendown()
                            turtle.forward(yBotOuter)
                            turtle.penup()
                        elif yTopInner + yInnerCenter >= yTopOuter + yOuterCenter and yBotInner + yInnerCenter <= yBotOuter + yOuterCenter:
                            print("inner ellipse contains outer ellipse")
                        if yOuterCenter >= yTopInner + yInnerCenter >= yBotOuter + yOuterCenter:
                            print("top inner is below outer center and above bot outer")
                            turtle.penup()
                            turtle.setpos(x, yOuterCenter)
                            turtle.pendown()
                            turtle.forward((yTopInner + yInnerCenter) - yOuterCenter)
                            turtle.penup()
                    else:
                        print("inner ellipse's domain does not contain this x value")
                        turtle.penup()
                        turtle.setpos(x, yOuterCenter)
                        turtle.pendown()
                        turtle.forward(yBotOuter)
                        turtle.penup()

            if Q4:
                if xOuterCenter <= x <= xOuterRadius + xOuterCenter:  # if x is in the domain of Q4
                    print("\tQ4")
                    yTopOuter = yOuterRadius * (1 - ((x - xOuterCenter) / xOuterRadius) ** 2) ** .5
                    yBotOuter = -yOuterRadius * (1 - ((x - xOuterCenter) / xOuterRadius) ** 2) ** .5
                    yTopInner = yInnerRadius * (1 - ((x - xInnerCenter) / xInnerRadius) ** 2) ** .5
                    yBotInner = -yInnerRadius * (1 - ((x - xInnerCenter) / xInnerRadius) ** 2) ** .5
                    print("yTopOuter, yBotOuter, yTopInner, yBotInner: ", yTopOuter, yBotOuter, yTopInner, yBotInner)
                    if -xInnerRadius + xInnerCenter <= x <= xInnerRadius + xInnerCenter:
                        if yBotOuter + yOuterCenter <= yBotInner + yInnerCenter <= yOuterCenter:
                            print("bot inner is below outer center and above bot outer")
                            turtle.penup()
                            turtle.setpos(x, yBotOuter)
                            turtle.pendown()
                            turtle.forward((yBotInner + yInnerCenter) - (yBotOuter + yOuterCenter))
                            turtle.penup()
                        elif yTopInner + yInnerCenter <= yBotOuter + yOuterCenter and yBotInner + yInnerCenter <= yBotOuter + yOuterCenter:
                            print("top and bot inner are below bot outer")
                            turtle.penup()
                            turtle.setpos(x, yOuterCenter)
                            turtle.pendown()
                            turtle.forward(yBotOuter)
                            turtle.penup()
                        elif yTopInner + yInnerCenter > yOuterCenter and yBotInner + yInnerCenter > yOuterCenter:
                            print("top and bot inner are above outer center")
                            turtle.penup()
                            turtle.setpos(x, yOuterCenter)
                            turtle.pendown()
                            turtle.forward(yBotOuter)
                            turtle.penup()
                        elif yTopInner + yInnerCenter >= yTopOuter + yOuterCenter and yBotInner + yInnerCenter <= yBotOuter + yOuterCenter:
                            print("inner ellipse contains outer ellipse")
                        if yOuterCenter >= yTopInner + yInnerCenter >= yBotOuter + yOuterCenter:
                            print("top inner is below outer center and above bot outer")
                            turtle.penup()
                            turtle.setpos(x, yOuterCenter)
                            turtle.pendown()
                            turtle.forward((yTopInner + yInnerCenter) - yOuterCenter)
                            turtle.penup()
                    else:
                        print("inner ellipse's domain does not contain this x value")
                        turtle.penup()
                        turtle.setpos(x, yOuterCenter)
                        turtle.pendown()
                        turtle.forward(yBotOuter)
                        turtle.penup()

        turtle.right(90)


turtle.penup()
turtle.setpos(-200, 0)
turtle.pendown()
turtle.forward(400)
turtle.penup()
turtle.setpos(0, -200)
turtle.pendown()
turtle.left(90)
turtle.forward(400)
turtle.right(90)
turtle.penup()
turtle.pen(1)
#for i in range(1, 10):
#Shape.makeSliver(0, 90, 90, 0, 0, 20, 40, 0, 40, True, True, True, True, 0, 0, 0)

#for i in range(1, 11):  # top sliver of emoji's face
    #Shape.makeSliver(0, 50, 50, 0, 0, 50, 50, 0, 50, True, True, True, True, 0, 0, 0)
#for i in range(1, 11):  # 2nd to top sliver of emoji's face
    #Shape.makeSliver(0, 50, 50, 0, 0, 50, 50, 0, 50, True, True, True, True, 0, 0, 0)
#for i in range(1, 16):  # middle sliver of emoji's face
    #Shape.makeSliver(0, 90, 90, 0, 30+i, 200, 200, 0, 210, True, True, True, True, 255, int(229 - 3.4*i), int(63.75 - 1.7*i))
for i in range(1, 31):  # 2nd to bottom sliver of emoji's face
    Shape.makeSliver(0, 90, 90, 0, 10+i, 200, 200, 0, 220, True, True, True, True, int(254.5 - 1.25*i), int(178.5 - 2.55*i), 38)
#for i in range(0,10): #bottom sliver of emoji's face
    #Shape.makeSliver(0, 90, 90, 0, i, 200, 200, 0, 130, True, True, True, True, 230, 128, int(38 - 3.8*i))

#for i in range(1, 10):
    #Shape.makeSliver(0, 40-4*i, 20-2*i, 0, 90, 90, 90, 0, 0, True, True, True, True, 255, 255, int(204 - 2.5*i))
#Shape.makeSliver(0, 40, 20, 0, 90, 90, 90, 0, 0, True, True, True, True, 0, 0, 0)
#turtle.penup()
#for i in range(0, 10):
    #Shape.makeSliver(0, 100-i, 100-i, 0, 0, 90, 90, 0, 0, True, True, True, True, int(178.5 + 5.1*i), int(102 + 2.55*i), 0)
#for i in range(10):
    #Shape.makeSliver(0, 90, 100-i, 0, 0, 90, 90, 0, 0, True, True, False, False, int(242.25 - 1.275*i), int(178.5 + 5.1*i), int(38.25 + 2.55*i))
#turtle.pensize(5)
#for i in range(6, 100, 5):
    #Shape.makeRing(0, (101 - i), (100 - i), 0, 0, int(127.5 + 1.275 * i), int(127.5 + 1.275 * i), int(51 + 1.53 * i), True, True, True, True, )

turtle.penup()
turtle.setposition(-100, -200)
turtle.color(0, 0, 0)
turtle.write("Enjoy the Emoji!", font=("Times New Roman", 20, "normal"))
turtle.hideturtle()
turtle.done()
