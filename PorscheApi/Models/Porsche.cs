using System;
using System.Collections.Generic;

namespace PorscheApi;

public partial class Porsche
{
    public int Id { get; set; }

    public string Model { get; set; } = null!;

    public string Years { get; set; } = null!;

    public string Description { get; set; } = null!;

    public string Image { get; set; } = null!;

    public string GetModel()
    {
        return this.Model;
    }
    public string GetYears()
    {
        return this.Years;
    }
    public string GetDescription()
    {
        return this.Description;
    }
    public string GetImage()
    {
        return this.Image;
    }
}
